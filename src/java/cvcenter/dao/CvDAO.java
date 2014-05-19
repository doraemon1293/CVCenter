/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Cv;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for Cv entity
 */
public interface CvDAO {

    //dont need list because loading student will load all of his CV as well.
    public Cv getCVById(int id);

    public void saveCV(Cv cv);

    public void deleteCV(Cv cv);
}
