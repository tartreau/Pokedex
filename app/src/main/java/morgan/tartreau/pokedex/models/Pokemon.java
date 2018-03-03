package morgan.tartreau.pokedex.models;

/**
 *
 *
 */
public class Pokemon {
    private int id;
    private int number;
    private String name;
    private String url;
    private int weight;
    private int base_experience;
    private int height;

    public Pokemon (int id, String name, int base_experience, int height, int weight){
        this.id=id;
        this.name=name;
        this.base_experience=base_experience;
        this.height=height;
        this.weight=weight;
    }

    public Pokemon (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getBase_experience() {
        return base_experience;
    }


    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String toString(){
        return "ID : "+id+"\nNom : "+name+"\nBase xp : "+base_experience+"\nWeight : "+weight+"\nHeight : "+height;
    }
}
