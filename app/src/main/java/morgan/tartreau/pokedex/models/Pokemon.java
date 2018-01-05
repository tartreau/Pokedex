package morgan.tartreau.pokedex.models;

/**
 *
 *
 */
public class Pokemon {

    private int number;
    private String name;
    private String url;
    private int weight;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlParties = url.split("/");
        return Integer.parseInt(urlParties[urlParties.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
