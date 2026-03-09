package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        // il faut lire le nom de la classe DAO depuis le fichier du configuration
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();

        // on doit utiliser la réflexion pour charger la classe DAO et aboutir l instance
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // Lecture du nom de la classe Métier depuis le fichier de configuration
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // faire injection de la DAO dans le Métier à laide de la réflexion
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        // Invocation de la méthode sur l'instance de Métier et affichage du résultat
        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}