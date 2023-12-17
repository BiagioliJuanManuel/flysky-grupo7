package com.grupo2.flysky.service;

import com.grupo2.flysky.dto.requestDto.ClientRequestDto;
import com.grupo2.flysky.dto.responseDto.*;
import com.grupo2.flysky.entity.Client;
import com.grupo2.flysky.entity.Flight;
import com.grupo2.flysky.entity.Ticket;
import com.grupo2.flysky.exception.DataBaseIsEmptyException;
import com.grupo2.flysky.repository.IClientRepository;
import com.grupo2.flysky.repository.IFlightRepository;

import com.grupo2.flysky.repository.ITicketRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlyskyService implements IFlySkyService {

    private final IFlightRepository flightRepository;
    private final IClientRepository clientRepository;
    private final ITicketRepository ticketRepository;
    private final ModelMapper mapper;

    public FlyskyService(IFlightRepository flightRepository, IClientRepository clientRepository, ITicketRepository ticketRepository) {
        this.mapper = new ModelMapper();
        this.flightRepository = flightRepository;
        this.clientRepository = clientRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<FlightDto> findAllFlights() {
        List<Flight> list = flightRepository.findAll();

        if (list.isEmpty()){
            throw new DataBaseIsEmptyException("No se encontraron vuelos disponibles.");
        }

        return list.stream()
                .map(f -> mapper.map(f, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketReservedDto buyTicket(Long id, ClientRequestDto client){
        Optional<Flight> optionalFlight = flightRepository.findById(id);
        Client nuevo = mapper.map(client,Client.class);
        Client frecuente = new Client();
        Ticket boleto = new Ticket();

        Optional<Client> clienteExiste = clientRepository.findAll().stream().filter(client1 -> client1.getDocumentNumber().equals(nuevo.getDocumentNumber())).findFirst();

//        if (clientRepository.findAll().stream().anyMatch(c -> c.getDocumentNumber().equals(nuevo.getDocumentNumber()))){
        if (optionalFlight.isEmpty()){
            throw new DataBaseIsEmptyException("No hay vuelo con id: "+id);
        }
        if (clienteExiste.isPresent()){
            frecuente = clienteExiste.get();
            boleto.setClient(frecuente);
        }else {
            clientRepository.save(nuevo);
            boleto.setClient(nuevo);
        }
        boleto.setFlight(optionalFlight.get());
        ticketRepository.save(boleto);

        return new TicketReservedDto(
                boleto.getIdTicket(),
                boleto.getClient().getName(),
                boleto.getClient().getDocumentNumber(),
                boleto.getFlight().getPrice(),
                boleto.getFlight().getOrigin(),
                boleto.getFlight().getDestination()

        );
    }

    @Override
    public ResponseDto payment(Long idTicket, String paymentMethod) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(idTicket);
        Ticket ticket;
        LocalDateTime fechaActual = LocalDateTime.now();
        if (optionalTicket.isPresent()){
            ticket = optionalTicket.get();
            ticket.setPaymentMethod(paymentMethod);
            ticket.setPaymentDate(fechaActual.toLocalDate());
            if (ticket.getFlight().getFlightDate().isAfter(fechaActual.plusDays(7))){
                ticket.setReservation(true);
            }
            ticket.setFinalPrice(calcularPrecioConDescuento(ticket.getFlight().getFlightDate(),ticket.getFlight().getPrice()));
            ticketRepository.save(ticket);
        }else {
            throw new DataBaseIsEmptyException("No existe ticket con id: "+ idTicket);
        }
        return new ResponseDto("El ticket "+ticket.getIdTicket() + " fue pagado con éxito");
    }

    @Override
    public List<TicketDto> findClient(Long idClient) {
        Optional<Client> optionalClient = clientRepository.findById(idClient);

        if (optionalClient.isEmpty()){
            throw new DataBaseIsEmptyException("No se encontró el cliente solicitado.");
        }

        Client client = optionalClient.get();

        List<TicketDto> listTickets = client.getTicket().stream()
                .map(ticket -> mapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
               return listTickets;
    }

    public Double calcularPrecioConDescuento(LocalDateTime fechaParametro, Double precio) {
        // Obtener la fecha actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Calcular la diferencia en días
        long diasDiferencia = Math.abs(ChronoUnit.DAYS.between(fechaParametro, fechaActual));
//        System.out.println(diasDiferencia);

        // Calcular el descuento según la cantidad de días de diferencia
        double descuento = 0.0;
        if (diasDiferencia >= 120) {
            descuento = 0.4;
        } else if (diasDiferencia >= 90) {
            descuento = 0.3;
        } else if (diasDiferencia >= 60) {
            descuento = 0.2;
        } else if (diasDiferencia >= 30) {
            descuento = 0.1;
        }

        // Calcular el precio con descuento
        double precioOriginal = precio; // Puedes ajustar el precio original según tus necesidades

        return precioOriginal - (precioOriginal * descuento);
    }

    @Override
    public DailyReportDto findDailyReport(LocalDate date) {

        //obtener: numero de ventas, ingresos generados, destinos populares, tendencias de reserva.

        //obtener lista filtrada por fecha de tickets
        List<Ticket> listTickets = ticketRepository.findByPaymentDate(date);

        if (listTickets.size() == 0){
            throw new DataBaseIsEmptyException("No se encontraron registros de esa fecha");
        }

        //obtener número de ventas totales
        int paidTickets =(int) listTickets.stream()
                    .filter(t -> {
                        String paymentMethod = t.getPaymentMethod();
                        return paymentMethod != null && !paymentMethod.isEmpty();
                    })
                    .count();

        //obtener y sumar los ingresos generados totales
        long dailyIncome =(long) listTickets.stream()
                    .filter(ticket -> Objects.nonNull(ticket.getFinalPrice()))
                    .mapToDouble(Ticket::getFinalPrice)
                    .sum();

        //obtener destinos populares
        Map<String, Long> destinationCount = listTickets.stream()
                .collect(Collectors.groupingBy(t -> t.getFlight().getDestination(), Collectors.counting()));

        //filtrar de forma descendente los destinos sean reservados o no
        List<String> listPopularDestination = destinationCount.entrySet().stream()
                .sorted((entry1, entry2)-> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        //obtener destinos reservados
        Map<String, Long> reservedDestinationCount = listTickets.stream()
                .filter(t-> t.isReservation())
                .collect(Collectors.groupingBy(t -> t.getFlight().getDestination(), Collectors.counting()));

        //filtrar de forma descendente las reservas
        List<String> listPopularReserve = reservedDestinationCount.entrySet().stream()
                .sorted((entry1, entry2)-> Long.compare(entry2.getValue(), entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        DailyReportDto dailyReportDto = new DailyReportDto();
            dailyReportDto.setDailySalesNumber(paidTickets);
            dailyReportDto.setDailyIncome(dailyIncome);
            dailyReportDto.setPopularDestinations(listPopularDestination);
            dailyReportDto.setBookingTrends(listPopularReserve);
            return dailyReportDto;

    }
}
