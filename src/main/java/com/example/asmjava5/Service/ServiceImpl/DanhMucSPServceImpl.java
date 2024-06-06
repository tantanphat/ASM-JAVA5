package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Repository.DanhMucSPRepository;
import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Service.DanhMucSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucSPServceImpl implements DanhMucSPService {
    @Autowired
    private DanhMucSPRepository dmDao;
    @Autowired
    private DanhMucSPRepository danhMucSPRepository;

    @Override
    public List<DanhMucSP> findAllDMSP() {
        return dmDao.findAll();
    }

    @Override
    public DanhMucSP findDMSPByID(int id) {
        return dmDao.getAllDanhMucSP(id);
    }

    @Override
    public void createDMSP(DanhMucSP dmuc) {
        danhMucSPRepository.save(dmuc);
    }

    @Override
    public void updateDMSP(DanhMucSP dmuc) {
        DanhMucSP dm = danhMucSPRepository.getAllDanhMucSP(dmuc.getMaDM());
        dm.setTenDanhMuc(dmuc.getTenDanhMuc());
        danhMucSPRepository.save(dm);
    }

    @Override
    public void deleteSMSP(int madm) {
//        danhMucSPRepository.deleteById(madm);
    }
}
