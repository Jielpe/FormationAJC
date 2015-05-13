package exec;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.Util;

public class Exec {
	private static ApplicationContext context ;
	
	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		Util classutil = (Util) context.getBean("util");
		
		double a = classutil.getDouble("Entrez votre premi�re valeur : ");
		double b = classutil.getDouble("Entrez votre deuxi�me valeur : ");
		System.out.println(classutil.addition(a, b));
	}

}
