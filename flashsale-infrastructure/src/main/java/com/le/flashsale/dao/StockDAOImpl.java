
package com.le.flashsale.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.le.flashsale.converter.StockConverter;
import com.le.flashsale.converter.StockProductConverter;
import com.le.flashsale.entity.StockPO;
import com.le.flashsale.mapper.StockMapper;
import com.le.flashsale.stock.dao.StockDAO;
import com.le.flashsale.stock.dto.StockDTO;
import com.le.flashsale.stock.dto.StockProductDTO;

/**
 * Date 2020/11/16 7:03 下午
 * Author le
 */
@Service
public class StockDAOImpl implements StockDAO {

    @Resource
    private StockMapper stockMapper;

    @Resource
    private StockConverter converter;

    @Resource
    private StockProductConverter stockProductConverter;

    public int deleteByPrimaryKey(Long id) {
        return stockMapper.deleteByPrimaryKey(id);
    }

    public int insert(StockDTO stockDTO) {
        StockPO stockPO = converter.dto2Po(stockDTO);
        int num = stockMapper.insert(stockPO);
        stockDTO.setId(stockPO.getId());
        return num;
    }

    public int insertSelective(StockDTO stockDTO) {
        StockPO stockPO = converter.dto2Po(stockDTO);
        int num = stockMapper.insertSelective(stockPO);
        stockDTO.setId(stockPO.getId());
        return num;
    }

    public StockDTO selectByPrimaryKey(Long id) {
        return converter.po2Dto(stockMapper.selectByPrimaryKey(id));
    }

    public int updateByPrimaryKeySelective(StockDTO stockDTO) {
        return stockMapper.updateByPrimaryKeySelective(converter.dto2Po(stockDTO));
    }

    public int updateByPrimaryKey(StockDTO stockDTO) {
        return stockMapper.updateByPrimaryKey(converter.dto2Po(stockDTO));
    }

    @Override
    public List<StockProductDTO> getAllStock() {
        return stockProductConverter.pos2Dtos(stockMapper.getAllStock());
    }

    @Override
    public int deduceStockById(Long stockId, Integer num) {
        return stockMapper.deduceStockById(stockId, num);
    }
}