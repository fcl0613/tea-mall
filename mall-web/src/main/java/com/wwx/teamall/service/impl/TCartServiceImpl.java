package com.wwx.teamall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwx.teamall.entity.DO.CartDO;
import com.wwx.teamall.entity.DTO.DeleteCartBatchDTO;
import com.wwx.teamall.entity.TCart;
import com.wwx.teamall.mapper.CartMapper;
import com.wwx.teamall.mapper.TCartMapper;
import com.wwx.teamall.model.Result;
import com.wwx.teamall.service.TCartService;
import com.wwx.teamall.utils.HttpUtil;
import com.wwx.teamall.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wwx
 * @since 2023-03-27
 */
@Service
public class TCartServiceImpl extends ServiceImpl<TCartMapper, TCart> implements TCartService {


    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Result addCart(Integer goodsId) {
        Integer currentUserId = getCurrentUserId();
        int count = this.count(new LambdaQueryWrapper<TCart>()
                .eq(TCart::getGoodsId, goodsId)
                .eq(TCart::getUserId, currentUserId));
        if (count > 0) {
            return Result.failed("当前商品已在购物车中，请勿重复添加");
        }
        TCart cart = new TCart();
        cart.setUserId(currentUserId);
        cart.setGoodsId(goodsId);
        cart.setGoodsCount(1);
        this.save(cart);
        return Result.success();
    }

    @Override
    public Result updateCount(Integer id, Integer count) {
        this.update(new LambdaUpdateWrapper<TCart>()
                .eq(TCart::getId, id)
                .set(TCart::getGoodsCount, count));
        return Result.success();
    }

    @Override
    public Result getCartList() {
        List<CartDO> cartList = cartMapper.getCartList(getCurrentUserId());
        return Result.success(cartList);
    }

    @Override
    public Result deleteCart(Integer id) {
        this.removeById(id);
        return Result.success();
    }

    @Override
    public Result deleteCartBatch(DeleteCartBatchDTO deleteCartBatchDTO) {
        this.removeByIds(deleteCartBatchDTO.getList());
        return Result.success();
    }

    private Integer getCurrentUserId() {
        HttpServletRequest request = HttpUtil.getRequest();
        String authorization = request.getHeader("Authorization");
        return jwtUtil.getUserId(authorization);
    }
}
