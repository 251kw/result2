package com.shantery.result2.paging;

import static com.shantery.result2.util.Result2Constants.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class PagingUtil {
	/**
	 * @param currentPageNum 表示しようとしているページ番号
	 * @param totalRecordNum 全件数
	 * @param recordPerPage 1ページに表示するレコード数
	 * @param length ページングのリスト長
	 * @param params 現在の、ページ情報以外の検索条件のMap
	 * @return ビューに渡せば描画してくれるページネーションのオブジェクト
	 */

	public static PagingView generatePagingView(
			int currentPageNum, // 現在のページ番号
			int totalRecordNum, // データの総件数
			int recordPerPage, // 1ページ当たりの表示件数
			int length, // ページバーの表示件数
			Map<String, String> params) {
		String baseQueryString = toQueryString(params); // ?が入る
		String preAppendPageNum = baseQueryString + PREPAGE; // URLの後ろに?&page=を入れる文字列

		PagingView pagingView = new PagingView(); // PagingViewのインスタンス化
		int totalPageNum = (int) Math.ceil((double) totalRecordNum / (double) recordPerPage); // 総ページ数
		PagingView.setTotalPageNum(totalPageNum);
		if (currentPageNum > totalPageNum || currentPageNum < ONE) {
			currentPageNum = ONE;
		}
		pagingView.setTotalRecordNum(totalRecordNum); // データの総件数
		if (totalRecordNum == ZERO) {
			pagingView.setFromRecordNum((currentPageNum - ONE) * recordPerPage);
		} else {
			pagingView.setFromRecordNum((currentPageNum - ONE) * recordPerPage + ONE); // ○~×件の○の値
		}
		// 終端のページを表示するときはその終端の件数を出す。○~×件の×の値
		pagingView.setToRecordNum(
				currentPageNum * recordPerPage < totalRecordNum ? currentPageNum * recordPerPage : totalRecordNum);
		pagingView.setRecordPerPage(recordPerPage); // 1ページ当たりの表示件数
		pagingView.setCurrentPageNum(currentPageNum); // 現在のページ番号

		pagingView.setCanGoFirst(currentPageNum != ONE); // TOPに行ける1かどうか(true or false)
		pagingView.setFirstHref(preAppendPageNum + ONE); // 1ページ目のURLの文字列

		if (totalRecordNum == ZERO) {
			pagingView.setCanGoLast((currentPageNum - ONE) != totalPageNum); // Lastに行けるかどうか(true or false)
		} else {
			pagingView.setCanGoLast(currentPageNum != totalPageNum); // Lastに行けるかどうか(true or false)
		}
		pagingView.setLastHref(preAppendPageNum + totalPageNum); // 総ページ目のURLの文字列

		pagingView.setCanGoPrevious(currentPageNum != ONE); // 1個前に行けるかどうか(true or false)
		pagingView.setPreviousHref(preAppendPageNum + (currentPageNum - ONE)); // 1個前のページ目のURLの文字列

		if (totalRecordNum == ZERO) {
			pagingView.setCanGoNext((currentPageNum - ONE) != totalPageNum); // 1個後ろに行けるかどうか(true or false)
		} else {
			pagingView.setCanGoNext(currentPageNum != totalPageNum); // 1個後ろに行けるかどうか(true or false)
		}
		pagingView.setNextHref(preAppendPageNum + (currentPageNum + ONE)); // 1個後ろのページ目のURLの文字列

		pagingView.setPagingViewElements(
				generatePagingViewElements(currentPageNum, totalPageNum, length, preAppendPageNum));

		return pagingView;
	}

	/**
	 * Mapをクエリストリングに変換する。
	 * もしMapが空の場合、"?"のみを返却する。
	 * @param params クエリストリングの要素Map
	 * @return 生成されたクエリストリング
	 */
	protected static String toQueryString(Map<String, String> params) {
		return Query + params.entrySet().stream().map(Object::toString).collect(Collectors.joining(AND));
	}

	/**
	 * 現在のページがなるべく中央にくるようにページングのリストを生成する。
	 * @param currentPageNum 現在のページ番号
	 * @param totalPageNum 最終ページ番号
	 * @param length 表示するリストの長さ
	 * @param preAppendPageNum ページン番号を末尾に連結すれば正常なクエリストリングになる文字列(ex. ?foo=bar&page=)
	 * @return ページングのリスト
	 */
	protected static List<PagingViewElement> generatePagingViewElements(
			int currentPageNum,
			int totalPageNum,
			int length,
			String preAppendPageNum) {
		/* 偶数個のリストが要求された場合は現在のページが前寄せになる。
		 * 例) [] がついているのが現在のページ
		 *    << < 1 2 [3] 4 5 6 > >>
		 */
		int backSpan = (length - ONE) / TWO; // 左側に何個つけるか
		int forthSpan = (length - ONE) - backSpan; // 右側に何個つけるか

		int startIndex; // 一番左の番号
		int endIndex; // 一番右の番号

		if (currentPageNum - backSpan < ONE) {
			// 表示幅に従うと存在しないページ(0ページ以下)が生成されるので、1ページから始める
			startIndex = ONE;
			endIndex = length < totalPageNum ? length : totalPageNum;
		} else if (currentPageNum + forthSpan > totalPageNum) {
			// 表示幅に従うと存在しないページ(最終ページ以降)が生成されるので、表示領域を最終ページから逆算する
			startIndex = totalPageNum - (length - ONE) > ONE ? totalPageNum - (length - ONE) : ONE;
			endIndex = totalPageNum;
		} else {
			// その間なので、中央にcurrentPageNumがくるように配置する。
			// ページのリストの端に当たっていないので、単純に中央に来るような両端を考えればよい。
			startIndex = currentPageNum - backSpan;
			endIndex = currentPageNum + forthSpan;
		}
		return IntStream.range(startIndex, endIndex + ONE)
				.mapToObj(n -> new PagingViewElement(String.valueOf(n), preAppendPageNum + n))
				.collect(Collectors.toList());
	}
}
