package com.example.asmjava5.Repository;

import com.example.asmjava5.Entity.SanPham;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface SanPhamDemoRepository extends PagingAndSortingRepository<SanPham, Long> {

}
