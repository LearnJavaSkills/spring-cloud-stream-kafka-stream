package in.learnjavaskills.updatebookedstatusservice.repository;

import in.learnjavaskills.updatebookedstatusservice.dto.MovieTicketBookedSuccessfully;
import org.springframework.stereotype.Service;

@Service
public class MovieTicketRepository
{
    public MovieTicketBookedSuccessfully save(MovieTicketBookedSuccessfully movieTicketBookedSuccessfully) {
        System.out.println("Saving movie ticket : " + movieTicketBookedSuccessfully.toString());
        return movieTicketBookedSuccessfully;
    }
}
