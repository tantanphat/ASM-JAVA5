package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Repository.DanhMucSPDao;
import com.example.asmjava5.Entity.DanhMucSP;
import com.example.asmjava5.Service.DanhMucSPService;
import com.example.asmjava5.Service.DanhMucSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucSPServceImpl implements DanhMucSPService {
    @Autowired
    private DanhMucSPDao dmDao;

    @Override
    public List<DanhMucSP> findAllDMSP() {
        return dmDao.findAll();
    }
}
