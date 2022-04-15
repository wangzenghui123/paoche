package com.example.paoche.service.impl;

import com.example.paoche.dao.HomeDao;
import com.example.paoche.dao.PermissionDao;
import com.example.paoche.entity.SysPermission;
import com.example.paoche.service.HomeService;
import com.example.paoche.vo.resp.HomeRespVO;
import com.example.paoche.vo.resp.PermissionRespNodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public HomeRespVO getHomeMenus() {
        HomeRespVO homeRespVO = new HomeRespVO();
        List<SysPermission> allPermissionsList = permissionDao.findAllPermissions();
        List<PermissionRespNodeVO> menuTreeList = getMenuTreeList(allPermissionsList);
        homeRespVO.setMenus(menuTreeList);
        return homeRespVO;
    }
    public List<PermissionRespNodeVO> getMenuTreeList(List<SysPermission> allPermissionsList){
        List<PermissionRespNodeVO> permissionRespNodeVOList = new ArrayList<>();
        if(allPermissionsList != null && allPermissionsList.size() > 0){
            for (SysPermission sysPermission : allPermissionsList) {
                if(sysPermission.getPid() != null && sysPermission.getPid().equals("0")){
                    PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                    permissionRespNodeVO.setId(sysPermission.getId());
                    permissionRespNodeVO.setTitle(sysPermission.getName());
                    List<PermissionRespNodeVO> children = getChildren(sysPermission.getId(), allPermissionsList);
                    permissionRespNodeVO.setChildren(children);
                    permissionRespNodeVOList.add(permissionRespNodeVO);
                }
            }
        }
        return permissionRespNodeVOList;
    }

    public List<PermissionRespNodeVO> getChildren(String pid,List<SysPermission> allPermissionList){
        List<PermissionRespNodeVO> permissionRespNodeVOList = new ArrayList<>();
        if(allPermissionList != null && allPermissionList.size() > 0){
            for (SysPermission sysPermission : allPermissionList) {
                if(sysPermission.getPid().equals(pid)){
                    PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                    permissionRespNodeVO.setId(sysPermission.getId());
                    permissionRespNodeVO.setTitle(sysPermission.getName());
                    permissionRespNodeVO.setUrl(sysPermission.getUrl());
                    permissionRespNodeVO.setChildren(getChildren(sysPermission.getId(),allPermissionList));
                    permissionRespNodeVOList.add(permissionRespNodeVO);
                }
            }
        }
        return  permissionRespNodeVOList;
    }
}
