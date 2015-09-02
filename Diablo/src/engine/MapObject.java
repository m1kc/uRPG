/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

/**
 *
 * @author Makc
 */
public class MapObject
{
    int x = 0;
    int y = 0;
    int i = 0;
    boolean isAvailable = true;

    public MapObject()
    {

    }

    public MapObject(int a, int b, int c, boolean d)
    {
        x = a;
        y = b;
        i = c;
        isAvailable = d;
    }
}
