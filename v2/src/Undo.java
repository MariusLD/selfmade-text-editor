public class Undo extends Command{

    public Undo(Application a, Editeur e){
        super(a, e);
    }

    @Override
    public void execute(){
        getEditeur().undo();
    }
}
