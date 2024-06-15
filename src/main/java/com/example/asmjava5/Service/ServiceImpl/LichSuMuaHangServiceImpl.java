package com.example.asmjava5.Service.ServiceImpl;

import com.example.asmjava5.Entity.HoaDon;
import com.example.asmjava5.Entity.KhachHang;
import com.example.asmjava5.Model.request.LichSuMuaHang;
import com.example.asmjava5.Service.LichSuMuaHangService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LichSuMuaHangServiceImpl implements LichSuMuaHangService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Object> getAllHoaDonByEmail(String email) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("sp_LichSuMuaHangCuaKhachHangTheoEmail")
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .setParameter("email", email);

        return storedProcedureQuery.getResultList();
    }

    @Override
    public List<Object> getAllItemsSP(String MaHDBan,String email) {
        StoredProcedureQuery storedProcedureQuery = entityManager
                .createStoredProcedureQuery("sp_LichSuChiTietHoaDonMuaHang")
                .registerStoredProcedureParameter("MaHDBan", String.class, ParameterMode.IN)
                .setParameter("MaHDBan", MaHDBan)
                .registerStoredProcedureParameter("Email", String.class, ParameterMode.IN)
                .setParameter("Email", email);
        return storedProcedureQuery.getResultList();
    }


}