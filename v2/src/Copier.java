public class Copier extends Command{

    public Copier(Application a, Editeur e){
        super(a, e);
    }

    @Override
    public void execute(){
        getApplication().setClipboard(getEditeur().getSelection());
        getEditeur().getCurseur().refresh(getEditeur().getBuffer());
    }
}