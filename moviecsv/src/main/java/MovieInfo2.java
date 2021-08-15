import com.opencsv.bean.CsvBindByName;

public class MovieInfo2 implements Comparable<MovieInfo2> {


     public  MovieInfo2(String name, String running_time, String rating) {
        this.name = name;
        this.running_time = Double.parseDouble(running_time);
        this.rating = Double.parseDouble(rating);
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

    @Override
    public int compareTo(MovieInfo2 o) {
        double diff = rating - o.rating;
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        }
        return 0;
    }


    private String name;
    private double running_time;
    private double rating;

}
