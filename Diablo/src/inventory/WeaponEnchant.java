/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventory;

/**
 *
 * @author Makc
 */
public class WeaponEnchant
{
    String name;

    int damage; // Повреждение

    int level; // Уровень. Если вещь ниже этого уровня - не энчится.

    int strength;  // Сила
    int agility;   // Ловкость
    int intellect; // Интеллект
    int stamina;   // Выносливость
    int spirit;    // Боевой дух

    // Все величины, кроме уровня, могут быть также отрицательными.
}
