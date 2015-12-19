package com.asymons.SimpleCrawler;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * Hello world!
 *
 */
public class App extends WebCrawler {
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|gif|jpg" + "|png|mp3|mp3|zip|gz))$");
	Scanner sc = new Scanner(System.in);
	String x = sc.nextLine();
	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.startsWith("http://windsor.craigslist.ca/search/vga/");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			boolean hi = false;
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();
//			System.out.println(text);
			Pattern pattern = Pattern.compile("(Xbox)|(\\$+\\d+)");
		    Matcher matcher = pattern.matcher(text);
		    while (matcher.find()) {
		    	if(matcher.group().equals("Xbox")){
		    		System.out.print(matcher.group());
		    		hi = true;
		    	}else if(hi == true){
		    		hi = false;
		    		System.out.println(" " + matcher.group());
		    	}
		    }
//			Pattern pattern = Pattern.compile("\\$+\\d+");
//		    Matcher matcher = pattern.matcher(html);
//		    while (matcher.find()) {
//		    	System.out.println(matcher.find());
//		    }
//			System.out.println("Text length: " + text.length());
//			System.out.println("Html length: " + html.length());
//			System.out.println("Number of outgoing links: " + links.size());
		}
	}
}
