/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.entity.Cv;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * The service providers for database transactions related to CV
 * entity The functions are just corresponding to the DAO class, because the
 * system's functionality is basic.
 */
public interface CvService {

    public Cv getCVById(int id);

    public void saveCV(Cv cv);

    public void deleteCV(Cv cv);
}
