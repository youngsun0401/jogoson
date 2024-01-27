public class Main {
    public static void main(String[] args){
        System.out.println("Jogoson Jogosonimnida");

        String str = " \r \n \"s\ttr \t";

        Jogoson j = new Jogoson();
        Object o = j.parseValue(str);

        System.out.println(o);

        System.out.println("BYE WORLD !");
    }
}
