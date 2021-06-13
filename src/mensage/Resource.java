package mensage;

public class Resource {
    
    private static final String MSG_ERRO_REMOVE = "There are no items to remove";
	private static final String MSG_ERRO = "There is no way to move this item";
	private static final String PATH_IMAGE = "https://image.freepik.com/fotos-gratis/cortar-pizza-no-preto_23-2147749510.jpg";
	private static final String PATH_IMAGE_LOGO = "/imagens/pizza.jpg";
    private static final String TITLE = "Pizzaria Decorator";

    public String getErro()
    {
        return MSG_ERRO;
    }

    public String getErroRemove()
    {
        return MSG_ERRO_REMOVE;
    }

    public String getPathImage()
    {
        return PATH_IMAGE;
    }

    public String getPathLogo()
    {
        return PATH_IMAGE_LOGO;
    }

    public String getTitle()
    {
        return TITLE;
    }
}
