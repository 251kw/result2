package com.shantery.result2.util;

import static com.shantery.result2.util.Result2Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;

/**
 * Result2システムで使用する共通クラス
 * Result2固有の共通した処理はこのクラスに集約
 */
public class Result2Util {

	/** インスタンス生成禁止 **/
	private Result2Util() {}
	/** ページ番号の初期値 **/
	private static final String DEFAULT_PEGE = "1";
	/** 表の表示列数 **/
	@Value("${app.columnsize}")
	private static int columnSize;

	/**
	 * 現在のページを返却します。
	 * @param page 遷移先のページ番号
	 * @return 現在ページ
	 */
	public static int getCurrentPage(String page) {
		// 現在いるページ番号の初期化
		var tmpPage = Optional.ofNullable(page).orElse(DEFAULT_PEGE);
		return Integer.parseInt(tmpPage);
	}

	/**
	 * 検索ワードを返すメソッド
	 * もしnullだったら空文字が入るようにする
	 * @param word 検索ワード
	 * @return 検索ワード
	 */
	public static String getSearchWord(String word) {
		// 検索ワードの初期化
		var tmpWord = Optional.ofNullable(word).orElse(EMPTY);
		return tmpWord;
	}

	/**
	 * 表示する見出しを返却する
	 * 列数名はapplication.propertiesより取得
	 * @param columns 列名（当クラスで設定ファイルからインジェクションすることは不可の為、引数で受け取る）
	 * @return 表示する見出し
	 */
	public static List<String> getColumnName(String columns) {

		List<String> columnList = new ArrayList<>();
		String[] columnArrays = columns.split(",");
		// 表示する件数分繰り返す
		Arrays.stream(columnArrays).forEach(columnList::add);
		// 表示する見出しを返却
		return columnList;
	}

	/**
	 * DBから取得したListの内容を変換する（Result2型->List型）
	 * 理由：Thymeleafで項目を可変にする為に実装
	 * @param list DBから取得したリスト
	 * @param columns 列名（当クラスで設定ファイルからインジェクションすることは不可の為、引数で受け取る）
	 * @return 変換後のリスト
	 */
	public static List<List<String>> convBeanToList(List<String> list, String columns) {

		List<List<String>> listResult2 = new ArrayList<>(); // 表示内容がすべて入ったリスト
		List<String>       result2     = new ArrayList<>(); // 1行分の表示内容

		// Result2の内容をListに変換
		/*result2 = list.stream().map(Result2 -> Result2.getId().toString()).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getDate).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getNationality).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getEmployment).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getCommercial_distribution).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getCost).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getAge).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getClosest_station).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getSubject).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getText).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getSender).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp1).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp2).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp3).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp4).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp5).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp6).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp7).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp8).collect(Collectors.toList());
		result2 = list.stream().map(Result2::getTemp9).collect(Collectors.toList());*/


		// サンプルデータ
		String[] smp1 = {"1", "2019-07-08 15:52:32", "JPN", "派遣", "商流", "100", "28", "新宿駅", "件名", "本文", "田中"};
		String[] smp2 = {"2", "2019-07-16 14:18:58", "USA", "常駐", "商流", "1500", "35", "Grand Central Station", "お腹すいた", "初めまして", "ボンジュール鈴木"};
		String[] smp3 = {"3", "2019-07-16 14:24:47", "JPN", "フリー", "商流", "5000", "33", "東京駅", "携帯について", "いつもお世話になっております。", "胡桃沢"};
		List<String> smpList1 = Arrays.asList(smp1);
		List<String> smpList2 = Arrays.asList(smp2);
		List<String> smpList3 = Arrays.asList(smp3);
		listResult2.add(smpList1);
		listResult2.add(smpList2);
		listResult2.add(smpList3);
		// 表示内容がすべて入ったリストを返却
		return listResult2;
	}
}