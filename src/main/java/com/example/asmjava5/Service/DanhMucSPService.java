package com.example.asmjava5.Service;


import com.example.asmjava5.Entity.DanhMucSP;

import java.util.List;

public interface DanhMucSPService {
    List<DanhMucSP> findAllDMSP();

    DanhMucSP findDMSPByID(int id);

    void createDMSP(DanhMucSP dmuc);

    void updateDMSP(DanhMucSP dmuc);

    void deleteSMSP(int madm);
}
