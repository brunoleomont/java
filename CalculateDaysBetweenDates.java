import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.EnumSet;

public class MyClass {
    public static void main(String args[]) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);

        List<LocalDate> datesBeetwen = getDatesBetween(startDate, endDate);

        List<LocalDate> diasNaoUteis = List.of(LocalDate.of(2022, 6, 27));
        
        List<LocalDate> diasUteis = List.of(LocalDate.of(2022, 7, 17));
        
        List<LocalDate> weekends = getWeekendDaysBetweenDates(startDate, endDate);

        TreeSet<LocalDate> diasDeDiferenca = new TreeSet<>();
        diasDeDiferenca.addAll(datesBeetwen);
        diasDeDiferenca.removeAll(diasNaoUteis);
        diasDeDiferenca.removeAll(weekends);
        diasDeDiferenca.addAll(diasUteis);
        
        diasDeDiferenca.forEach(dia -> {
            System.out.println(dia);
        });
    }
    
    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        final long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
            .limit(numOfDaysBetween)
            .mapToObj(i -> startDate.plusDays(i))
            .collect(Collectors.toList());
    }
    
    public static List<LocalDate> getWeekendDaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        return startDate.datesUntil(endDate)
            .filter(d -> weekend.contains(d.getDayOfWeek()))
            .collect(Collectors.toList());
    }

}
