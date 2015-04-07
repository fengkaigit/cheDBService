package com.chedb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chedb.dao.ServiceDao;
import com.chedb.model.ModelProvider;
import com.chedb.model.ModelService;
import com.chedb.model.ModelServiceProvider;
import com.chedb.model.ModelUserAppraise;
import com.chedb.service.ServiceIA;

@Service("serviceImpl")
@Transactional(rollbackFor = Exception.class)
public class ServiceImpl implements ServiceIA {
	@Resource(name = "serviceDaoImpl")
	private ServiceDao serviceDao;

	@Override
	public ModelService getServiceInfo(String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getServiceInfo(serviceId);
	}

	@Override
	public List<ModelServiceProvider> getServiceListByProvider(String providerId)
			throws Exception {
		return serviceDao.getServiceListByProvider(providerId);
	}

	@Override
	public boolean saveServiceProviderLink(List<String> serviceIdlist,
			String serviceClassId, String providerId) throws Exception {
		return serviceDao.saveServiceProviderLink(serviceIdlist,
				serviceClassId, providerId);
	}

	@Override
	public List<ModelService> getServiceListByClassId(String classId,
			String carId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getServiceListByClassId(classId, carId);
	}

	@Override
	public int getProviderCountByServiceId(String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao.getProviderCountByServiceId(serviceId);
	}

	@Override
	public List<ModelProvider> getProviderListByServiceId(String serviceId,
			String start, String count, String latitude, String longitude)
			throws Exception {
		// TODO Auto-generated method stub
		List<ModelProvider> providers = serviceDao.getProviderListByServiceId(
				serviceId, start, count, latitude, longitude);
		for (ModelProvider modelProvider : providers) {
			if (modelProvider.getImgIdListStr() != null
					&& !modelProvider.getImgIdListStr().equalsIgnoreCase("")) {
				modelProvider.setDefaultimg(modelProvider.getImgIdListStr()
						.split(",")[0]);
			} else {
				modelProvider.setDefaultimg(null);
			}
		}
		return providers;
	}

	@Override
	public ModelUserAppraise doAppraise(String serviceClassId,
			String serviceId, String content, String userId) throws Exception {
		// TODO Auto-generated method stub
		return this.serviceDao.doAppraise(serviceClassId, serviceId, content,
				userId);
	}

	@Override
	public int getAppraiseCountByServiceId(String serviceClassId,
			String serviceId) throws Exception {
		// TODO Auto-generated method stub
		return serviceDao
				.getAppraiseCountByServiceId(serviceClassId, serviceId);
	}

	@Override
	public List<ModelUserAppraise> getAppraiseListByServiceId(
			String serviceClassId, String serviceId, String start, String count)
			throws Exception {
		// TODO Auto-generated method stub
		return this.serviceDao.getAppraiseListByServiceId(serviceClassId,
				serviceId, start, count);
	}

	@Override
	public List<ModelService> recommendService(String classId, String carId,
			String yearid, Integer moverange) throws Exception {
		// TODO Auto-generated method stub
		List<ModelService> services = this.serviceDao.getServiceListByClassId(
				classId, yearid);
		// 同一车型的多个同类服务，其起始公里数应该是相同的
		if (services != null && !services.isEmpty()) {
			int fristKm = services.get(0).getFirstKm();
			// 得到多个服务项目中最小的周期
			int minPeriod = services.get(0).getPeriodKm();
			for (int i = 1; i < services.size(); i++) {
				if (minPeriod > services.get(i).getPeriodKm()) {
					minPeriod = services.get(i).getPeriodKm();
				}
			}
			// 得到用户输入的里程对应于哪个里程节点
			int nowMark = fristKm;
			for (; nowMark < moverange; nowMark += minPeriod) {// 这个循环是为了找到第一个大于当前里程的服务节点
				;
			}
			if ((nowMark - moverange) > minPeriod / 2) {// 这里是判断当前里程离哪个服务节点最近
				if (fristKm < nowMark) {
					nowMark = nowMark - minPeriod;
				}
			}
			// 查找这个服务节点有几个服务，如果有多个，则服务明细最多的服务就是推荐的服务（优秀做服务明细多的服务）
			int pos = 0;// 根据用户输入的当前里程和各个保养的开始公里数以及保养周期，判断当前应该做哪个保养
			for (int i = 1; i < services.size(); i++) {
				ModelService s = services.get(i);
				int tempMark = s.getFirstKm();
				for (; nowMark >= tempMark; tempMark += s.getPeriodKm()) {
					if (nowMark == tempMark) {
						if (s.getItemNum() > services.get(pos).getItemNum()) {
							pos = i;
						}
					}
				}
			}

			if (services.size() <= pos) {
				return new ArrayList<ModelService>();
			}
			// 推荐文字提醒
			String title;
			if (nowMark >= moverange) {
				title = "建议里程为" + nowMark + "公里时做以上服务";
			} else {
				title = "建议里程为" + nowMark + "-" + moverange + "公里时做以上服务";
			}
			List<ModelService> modelServices = new ArrayList<ModelService>();
			ModelService modelService = services.get(pos);
			modelService.setMessage(title);
			modelServices.add(modelService);
			return modelServices;
		}
		return services;
	}

}
