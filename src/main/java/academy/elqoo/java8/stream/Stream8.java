package academy.elqoo.java8.stream;


import com.sun.deploy.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Stream8 {

    public static List<Integer> returnSquareRoot(List<Integer> numbers) {
        return numbers.stream()
                .map(Math::sqrt)
                .map(Double::intValue)
                .collect(Collectors.toList());
    }

    public static List<Integer> getAgeFromUsers(List<User> user) {
        return user.stream()
                .map(User::getAge)
                .collect(Collectors.toList());
    }

    public static List<Integer> getDistinctAges(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<User> getLimitedUserList(List<User> users, int limit) {
        return users.stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    public static Integer countUsersOlderThen25(List<User> users) {
        return Math.toIntExact(users.stream()
                .filter(u -> u.getAge() > 25)
                .count());
    }

    public static List<String> mapToUpperCase(List<String> strings) {
        return strings.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> integers) {
        return integers.stream()
                .reduce(Integer::sum)
                .orElse(null);
    }

    public static List<Integer> skip(List<Integer> integers, Integer toSkip) {
        return integers.stream()
                .skip(toSkip)
                .collect(Collectors.toList());
    }

    public static List<String> getFirstNames(List<String> names) {
        return names.stream()
                .map(n -> n.split(" ")[0])
                .collect(Collectors.toList());
    }

    public static List<String> getDistinctLetters(List<String> names) {
        return names.stream()
                .map(s -> s.chars())
                .flatMap(intStream -> intStream.mapToObj(charCode -> (char) charCode))
                .distinct()
                .map(n -> new String(new char[]{n}))
                .collect(Collectors.toList());
    }


    public static String separateNamesByComma(List<User> users) {
        return users.stream()
                .map(u -> u.getName())
                .collect(Collectors.joining(", "));
    }

    public static double getAverageAge(List<User> users) {
        Integer integer = users.stream()
                .map(u -> u.getAge())
                .reduce(Integer::sum)
                .orElse(0);
        return integer / users.size();
    }

    public static Integer getMaxAge(List<User> users) {
        return users.stream()
                .map(u -> u.getAge())
                .max(Comparator.comparingInt(a -> a))
                .orElse(null);
    }

    public static Integer getMinAge(List<User> users) {
        return users.stream()
                .map(u -> u.getAge())
                .min(Comparator.comparingInt(a -> a))
                .orElse(null);
    }

    public static Map<Boolean, List<User>> partionUsersByGender(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(u -> u.isMale()));
    }

    public static Map<Integer, List<User>> groupByAge(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(User::getAge));
    }

    public static Map<Boolean, Map<Integer, List<User>>> groupByGenderAndAge(List<User> users) {
        return users.stream()
                .collect(Collectors.groupingBy(user -> user.isMale(), Collectors.groupingBy(User::getAge)));
    }

    public static Map<Boolean, Long> countGender(List<User> users) {
//        return users.stream()
//                .collect(Collectors.groupingBy(u->u.isMale()), Collectors.counting());
        return null;
    }


    public static boolean anyMatch(List<User> users, int age) {
        return users.stream()
                .anyMatch(u -> u.getAge() == age);
    }

    public static boolean noneMatch(List<User> users, int age) {
        return users.stream()
                .noneMatch(u -> u.getAge() == age);
    }

    public static Optional<User> findAny(List<User> users, String name) {
        return users.stream()
                .findAny();
    }

    public static List<User> sortByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public static Stream<Integer> getBoxedStream(IntStream stream) {
        return stream.boxed();
    }

    public static List<Integer> generateFirst10PrimeNumbers() {
        return IntStream.range(2, 100)
                .filter(Stream8::isPrime)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());
    }

    public static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, number / 2).noneMatch(i -> number % i == 0);
    }

    public static List<Integer> generate10RandomNumbers() {
        return new Random().ints(10)
                .boxed()
                .collect(Collectors.toList());
    }

    public static User findOldest(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .findFirst()
                .orElse(null);
    }

    public static int sumAge(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .mapToInt(u -> u)
                .sum();

    }

    public static IntSummaryStatistics ageSummaryStatistics(List<User> users) {
        return users.stream()
                .map(User::getAge)
                .mapToInt(u -> u)
                .summaryStatistics();
    }

}
