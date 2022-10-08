public class Redo extends Command{

    public Redo(Application a, Editeur e){
        super(a, e);
    }

    @Override
    public void execute(){
        getEditeur().redo();
    }
}