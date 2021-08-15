import com.opencsv.bean.CsvBindByName;

public class MovieInfo implements Comparable<MovieInfo> {
    @CsvBindByName(column = "name", required = true)
    private String name;
    @CsvBindByName(column = "running_time", required = true)
    private double running_time;
    @CsvBindByName(column = "rating", required = true)
    private double rating;

    @Override
    public int compareTo(MovieInfo o) {
        double diff = rating - o.rating;
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name=");
        sb.append(name);
        sb.append(",");
        sb.append("running_time=");
        sb.append(running_time);
        sb.append(",");
        sb.append("rating=");
        sb.append(rating);
        return  sb.toString();
    }
}
