/**
 * 
 */
package com.mb.auth.auth.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.mb.auth.auth.response.Organization;

/**
 * @author KABHISH
 *
 */
@Service
public class HierarchyLoader {

	@Autowired
	private ApplicationContext ctx;
	
	public Organization getHierachy() throws IOException {
		Organization rootOrganization = new Organization();

		Resource resource = ctx.getResource("classpath:sales_unit.csv");
		List<List<String>> records = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(resource.getFile().toPath())) {
			while (scanner.hasNextLine()) {
				records.add(getRecordFromLine(scanner.nextLine()));
			}
		}
		return rootOrganization;
	}




	private List<String> getRecordFromLine(String line) {
		List<String> values = new ArrayList<String>();
		try (Scanner rowScanner = new Scanner(line)) {
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		System.err.println(values);
		return values;
	}
}
