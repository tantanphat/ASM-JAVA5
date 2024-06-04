package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDonChiTiet;
import com.example.asmjava5.Service.ThongKeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongKeServiceImpl implements ThongKeService {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<HoaDonChiTiet> thongKeNhanVienChiTietThang(int thang) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("sp_NhanVien_CoDonThang")
                .registerStoredProcedureParameter("thang", Integer.class, ParameterMode.IN)
                .setParameter("thang", thang);

        return storedProcedure.getResultList();
    }

    @Override
    public List<HoaDonChiTiet> thongKeNhanVienCoDonThang(int thang) {
        StoredProcedureQuery storedProcedure = entityManager
                .createStoredProcedureQuery("sp_NhanVienThang")
                .registerStoredProcedureParameter("thang", Integer.class, ParameterMode.IN)
                .setParameter("thang", thang);

        return storedProcedure.getResultList();
    }
}
