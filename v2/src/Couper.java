public class Couper extends Command{
    
    public Couper(Application a, Editeur e){
        super(a, e);
    }

    @Override
    public void execute(){
        getApplication().setClipboard(getEditeur().getSelection());
        getEditeur().supprimerSelection();
    }
}
