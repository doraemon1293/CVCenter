/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.dao.CvDAO;
import cvcenter.entity.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * Implementation of the Service class for Cv Entity Very simple
 * and make user of the corresponding CvDAO class
 */
@Service("CvService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImplCvService implements CvService {

    @Autowired
    private CvDAO cvdao;

    public ImplCvService() {
    }

    @Override
    public Cv getCVById(int id) {
        return cvdao.getCVById(id);
    }

    @Override
    public void saveCV(Cv cv) {
        cvdao.saveCV(cv);
    }

    //need to remove the association with the job, avoiding deleting by mistake
    @Override
    public void deleteCV(Cv cv) {
        cv.getJobs().clear();
        cvdao.saveCV(cv);
        cvdao.deleteCV(cv);
    }
}
