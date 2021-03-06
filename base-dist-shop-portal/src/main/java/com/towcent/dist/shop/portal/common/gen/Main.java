package com.towcent.dist.shop.portal.common.gen;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.towcent.base.gen.component.GenTools;
import com.towcent.base.gen.entity.GenInterface;
import com.towcent.base.gen.entity.GenTemplate;

public class Main {

	public static void addLocalMap(Map<String, Object> model) {
		model.put("packageName", "com.towcent.dist.shop.portal");
		model.put("functionAuthor", "huangtao"); // 作者名称
		model.put("functionVersion", "0.0.1"); // 版本号
	}
	
	public static void main(String[] args) throws Exception {
		// String path = "D:\\base\\base-sample\\base-sample-portal\\InterfaceDoc.xlsx";
		String path =
		"D:\\Project\\new_28\\base-dist-shop\\trunk\\base-dist-shop\\base-dist-shop-portal\\InterfaceDoc.xlsx";

		List<GenInterface> list = GenTools.buildInterfaceList(path);

		System.out.println(list.size());

		List<GenTemplate> templateList = GenTools.getGenTemplateList();

		System.out.println(templateList.size());

		Boolean isCover = false;
		for (GenInterface inter : list) {
			Map<String, Object> model = GenTools.getDataModel(inter);
			
			addLocalMap(model);
			
			for (GenTemplate tpl : templateList) {
				if ((CollectionUtils.isEmpty(inter.getOutParamList())) && (tpl.getFileName().endsWith("Out.java")))
					continue;
				if ((CollectionUtils.isEmpty(inter.getSecOutParamList()))
						&& (tpl.getFileName().endsWith("OutSec.java")))
					continue;
				GenTools.generateToFile(tpl, model, isCover);
			}
		}
	}

}