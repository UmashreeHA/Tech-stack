/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvit;

import javax.ejb.Local;

/**
 *
 * @author umash
 */
@Local
public interface SessionBeanDemoLocal {

    int add(int a, int b);

    int sub(int a, int b);
    
}
