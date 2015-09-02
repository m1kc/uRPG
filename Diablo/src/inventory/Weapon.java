/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventory;

import java.util.Vector;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Makc
 */
public class Weapon
{
    String name = ""; // Имя

    int damage = 0; // Повреждение

    int level = 1;  // Уровень
    int weight = 0; // Вес

    int strength = 0;  // Сила
    int agility = 0;   // Ловкость
    int intellect = 0; // Интеллект
    int stamina = 0;   // Выносливость
    int spirit = 0;    // Боевой дух

    Vector enchants = new Vector(); // Энчанты

    Image icon = Image.createImage(1,1); // Картинка

}
