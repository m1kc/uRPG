/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package urpg;

import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author пользователь
 */
public class Unit implements GameConstants,GameConstantsForDirections
{

    public int state = STAND;
    public int direction;

    public Sprite s;

    public Animation stand,walk,attack,dead;

    public boolean isNPC = false;
    public Dialog NPCdialog = null;

    public int side = NEUTRAL;

    public boolean causesDamage = false;

    // Сила, Ловкость, Реакция, Адреналин, Выносливость
    int power,agility,reaction,adrenaline,stamina;
    // Физическая атака, Физическая защита, Шанс критического удара, Макс.здоровье, Здоровье
    int pAtt,pDef,critChance,maxHP,hp;
    // Интеллект, Внимательность, Харизма, Лидерство, Сообразительность
    int intellect,perception,charisma,leadership,wisdom;

    public int exp = 0;

    public Unit(Sprite s)
    {
        this.s = s;
        this.direction = DOWN;
    }

    public Unit(int direction, Sprite s)
    {
        this.direction = direction;
        this.s = s;
    }


    public void appendAnimation(Animation a)
    {
        if (direction==UP) s.setFrameSequence(a.up);
        if (direction==DOWN) s.setFrameSequence(a.down);
        if (direction==LEFT) s.setFrameSequence(a.left);
        if (direction==RIGHT) s.setFrameSequence(a.right);
    }

    public void appendStateAnimation()
    {
        if (state==STAND) appendAnimation(stand);
        if (state==WALK) appendAnimation(walk);
        if (state==ATTACK) appendAnimation(attack);
        if (state==DEAD) appendAnimation(dead);
    }

    public void tryChangeStateAndDirection(int newState, int newDirection)
    {
        if ((state==newState)&&(direction==newDirection)) return;

        if (state==ATTACK) return;
        if (state==DEAD) return; // "Мертвые не кусаются," - так, кажется, говорил Билли Бонс...

        state = newState;
        direction = newDirection;
        appendStateAnimation();
    }

    public void frameReaction()
    {
        if ((state==ATTACK)&&(s.getFrame()==0))
        {
            state = STAND;
            appendStateAnimation();
        }
        if ((state==ATTACK)&&(s.getFrame()==3))
        {
            causesDamage = true;
        }
    }

    public void setMainStats(int power, int agility, int reaction, int adrenaline, int stamina)
    {
        this.power = power;
        this.agility = agility;
        this.reaction = reaction;
        this.adrenaline = adrenaline;
        this.stamina = stamina;
        recalcStats();
    }

    public void setAdditionalStats(int intellect, int perception, int charisma, int leadership, int wisdom)
    {
        this.intellect = intellect;
        this.perception = perception;
        this.charisma = charisma;
        this.leadership = leadership;
        this.wisdom = wisdom;
    }

    public void recalcStats()
    {
        pAtt = (power + agility*3/4)*5;
        pDef = (agility/4 + reaction)*5;
        critChance = adrenaline*5;

        boolean flag = false;
        if (hp==maxHP) flag = true; // было ли здоровье полным в момент пересчета статов
        maxHP = stamina * 100;
        if (flag) hp = maxHP; // если было - восстанавливаем
    }

}
