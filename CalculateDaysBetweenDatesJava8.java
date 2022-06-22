import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.EnumSet;

public class MyClass {
    public static void main(String args[]) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusMonths(1);

        List<LocalDate> datesBeetwen = getDatesBetween(startDate, endDate);

        List<LocalDate> nonWorkingDays = new ArrayList<>();
        nonWorkingDays.add(LocalDate.now().plusDays(2));
        
        List<LocalDate> businessDays = new ArrayList<>();
        businessDays.add(LocalDate.now().plusDays(4));
        
        List<LocalDate> weekends = getWeekendDaysBetweenDates(startDate, endDate);

        TreeSet<LocalDate> daysDifference = new TreeSet<>();
        daysDifference.addAll(datesBeetwen);
        daysDifference.removeAll(nonWorkingDays);
        daysDifference.removeAll(weekends);
        daysDifference.addAll(businessDays);
        
        daysDifference.forEach(dia -> {
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
        return Stream.iterate(startDate, date -> date.plusDays(1))
            .limit(ChronoUnit.DAYS.between(startDate, endDate))
            .filter(d -> weekend.contains(d.getDayOfWeek()))
            .collect(Collectors.toList());
    }

}
