public class Actionneur {
    private Command command;
    public void lancerCommande(){
        command.execute();
    }
}
