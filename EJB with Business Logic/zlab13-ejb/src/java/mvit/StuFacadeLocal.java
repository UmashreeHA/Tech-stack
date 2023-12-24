/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvit;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author umash
 */
@Local
public interface StuFacadeLocal {

    void create(Stu stu);

    void edit(Stu stu);

    void remove(Stu stu);

    Stu find(Object id);

    List<Stu> findAll();

    List<Stu> findRange(int[] range);

    int count();

    void addStu(String usn, String name, String branch);
    
}
