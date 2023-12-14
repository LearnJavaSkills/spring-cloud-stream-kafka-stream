package in.learnjavaskills.bookmymovieservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;

public class MovieTicketBookingRequest
{
    private String movieName;
    private byte screenNumber;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime movieShowTime;
    private String seatNumber;
    private String user;


    public String getMovieName()
    {
        return movieName;
    }

    public void setMovieName(String movieName)
    {
        this.movieName = movieName;
    }

    public byte getScreenNumber()
    {
        return screenNumber;
    }

    public void setScreenNumber(byte screenNumber)
    {
        this.screenNumber = screenNumber;
    }

    public LocalDateTime getMovieShowTime()
    {
        return movieShowTime;
    }

    public void setMovieShowTime(LocalDateTime movieShowTime)
    {
        this.movieShowTime = movieShowTime;
    }

    public String getSeatNumber()
    {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber)
    {
        this.seatNumber = seatNumber;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MovieTicketBookingRequest that = (MovieTicketBookingRequest) o;
        return screenNumber == that.screenNumber && Objects.equals(movieName, that.movieName) && Objects.equals(movieShowTime, that.movieShowTime) && Objects.equals(seatNumber, that.seatNumber) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(movieName, screenNumber, movieShowTime, seatNumber, user);
    }

    @Override
    public String toString()
    {
        return "MovieTicketBookingRequest{" + "movieName='" + movieName + '\'' + ", screenNumber=" + screenNumber + ", movieShowTime=" + movieShowTime + ", seatNumber='" + seatNumber + '\'' + ", user='" + user + '\'' + '}';
    }
}
