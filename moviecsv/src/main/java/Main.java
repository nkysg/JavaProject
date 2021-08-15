
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;


import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.List;


public class Main {

    private static final int SIZE = 10;
    public static  void main1(String[] args) throws IOException, CsvValidationException {


        CSVReader reader = new CSVReader(new FileReader(args[0]));


        String[] nextLine;
        Queue<MovieInfo2> q = new PriorityQueue<>();
        while ((nextLine = reader.readNext()) != null) {
            MovieInfo2 info = new MovieInfo2(nextLine[0], nextLine[1], nextLine[2]);
            q.add(info);
            if (q.size() > SIZE) {
                q.poll();
            }
        }

        List<MovieInfo2> lists = new ArrayList<>();
        while (q.size() > 0) {
            MovieInfo2 info = q.poll();
            lists.add(info);
        }

        for (MovieInfo2 info : lists) {
            System.out.println(info);
        }
    }
    public static  void main2(String[] args) throws IOException {

        Queue<MovieInfo> q = new PriorityQueue<>();
        List<MovieInfo> movieInfos = new CsvToBeanBuilder<MovieInfo>(new FileReader(args[0]))
                .withType(MovieInfo.class).build().parse();
        for (MovieInfo info: movieInfos) {
            q.add(info);
            if (q.size() > SIZE) {
                q.poll();
            }
        }

        List<MovieInfo> lists = new ArrayList<>();
        while (q.size() > 0) {
            MovieInfo info = q.poll();
            lists.add(info);
        }

        for (MovieInfo info : lists) {
            System.out.println(info);
        }
    }

    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));

        Queue<MovieInfo2> q = new PriorityQueue<>();
        String line;
        while ((line = br.readLine()) != null) {
            List<String> strS = parseElem(line);
            if (strS.size() > 0) {
                if (strS.get(0).equals("name")) {
                    continue;
                }
            }
            MovieInfo2 info = new MovieInfo2(strS.get(0), strS.get(1), strS.get(2));
            q.add(info);
            if (q.size() > SIZE) {
                q.poll();
            }
        }

        List<MovieInfo2> lists = new ArrayList<>();
        while (q.size() > 0) {
            lists.add(q.poll());
        }

        for (MovieInfo2 info : lists) {
            System.out.println(info);
        }

    }

    private static List<String> parseElem(String line) {
        int cnt = 0;
        int pos = 0;
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                cnt++;
            }
            if (line.charAt(i) == ',') {
                if (cnt % 2 == 0) {
                    lists.add(line.substring(pos, i));
                    pos = i + 1;
                }
            }
        }
        if (pos < line.length()) {
            lists.add(line.substring(pos));
        }
        return lists;
    }

    List<String> split(String str, String delimiter) {
        List<String> lists = new ArrayList<>();
        int pos = 0, last = 0;
        while ((pos = str.indexOf(delimiter, pos)) != -1) {
            lists.add(str.substring(last, pos));
            last = pos + delimiter.length();
            pos = last;
        }
        if (last < str.length()) {
            lists.add(str.substring(last));
        }
        return lists;
    }
}
