package main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import interfaces.PizzaComponent;
import java.lang.ClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Principal {

	private HashMap<String, String> fileTypes;
	private static URL[] jars;

	public Principal() {
		this.fileTypes = new HashMap<String, String>();
		this.setJars(loadJarsUrl(fileTypes));
	}

	public void main(ArrayList<String> selectedItens) {

		PizzaComponent pizzaBase = new PizzaSimples();

		for (String itens : selectedItens) {

			if (fileTypes.containsKey(itens)) {
				String className = fileTypes.get(itens).toString();
				String packageName = className.toLowerCase().replace(itens, "") + "s";

				try {

					URLClassLoader sysLoader = getSysLoader(jars);
					pizzaBase = (PizzaComponent) Class.forName(packageName + "." + className, true, sysLoader)
							.getConstructor(PizzaComponent.class).newInstance(pizzaBase);

				} catch (SecurityException | IllegalArgumentException | InstantiationException | IllegalAccessException
						| InvocationTargetException | NoSuchMethodException | ClassNotFoundException error) {
					System.out.println(error);
				}
			}
		}
		pizzaBase.preparar();
	}

	public String[] getSabores() {
		String[] selectedItens = new String[fileTypes.size()];
		int posicao = 0;

		for (String sigla : fileTypes.keySet()) {
			selectedItens[posicao] = sigla;
			posicao++;
		}

		return selectedItens;
	}

	public static Set<String> listFilesUsingJavaIO(String dir) {
		return Stream.of(new File(dir).listFiles()).filter(file -> !file.isDirectory()).map(File::getName)
				.collect(Collectors.toSet());
	}

	public static URL[] loadJarsUrl(HashMap<String, String> fileTypes) {

		Set<String> plugins = listFilesUsingJavaIO("./src/plugins");
		URL[] jars = new URL[plugins.size()];

		for (String plugin : plugins) {
			int posicao = 0;

			try {
				jars[posicao] = (new File("./plugins/" + plugin)).toURI().toURL();

				String pathToString = jars[posicao].toString();
				String pluginName = pathToString.substring(pathToString.lastIndexOf('/') + 1).replace(".jar", "");
				String pluginType = pluginName.replace("Decorator", "").toLowerCase();

				fileTypes.put(pluginType, pluginName);
				posicao++;

			} catch (MalformedURLException error) {
				System.out.println(error);
			}
		}

		return jars;
	}

	public static URLClassLoader getSysLoader(URL[] jars) {
		URLClassLoader sysLoader = null;

		try {

			sysLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			Class<URLClassLoader> sysClass = URLClassLoader.class;

			for (URL jar : jars) {
				Method method = sysClass.getDeclaredMethod("addURL", URL.class);
				method.setAccessible(true);
				method.invoke(sysLoader, jar);
			}
		} catch (Exception error) {
			System.out.println(error);
		}
		return sysLoader;
	}

	public static URL[] getJars() {
		return jars;
	}

	public void setJars(URL[] jars) {
		Principal.jars = jars;
	}

}