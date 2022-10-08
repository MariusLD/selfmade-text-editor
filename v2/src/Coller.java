public class Coller extends Command{
    
    public Coller(Application a, Editeur e){
        super(a, e);
    }

    @Override
    public void execute(){
        getEditeur().modifierSelection(getApplication().getClipboard());
    }
}
