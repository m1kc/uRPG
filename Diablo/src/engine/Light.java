/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

/**
 *
 * @author Makc
 */
public class Light
{
    int x,y;
    int intensity;

    public Light()
    {
        x=y=intensity=0;
    }

    public Light(int a, int b, int c)
    {
        x = a;
        y = b;
        intensity = c;
    }
}
