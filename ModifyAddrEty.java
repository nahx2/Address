package address.view3;

import java.sql.Connection;
import java.sql.PreparedStatement;

import address.DBConnectionMgr;

public class ModifyAddrEty {

	public AddressVO modify(AddressVO vo) {
		System.out.println("ModifyAddrEty modify 호출 성공");
		DBConnectionMgr dbMgr = new DBConnectionMgr();
		Connection con = null;
		PreparedStatement pstmt = null;
		
//		int result = 0;
		StringBuilder sql = new StringBuilder();
		
		sql.append(" update MKADDRTB set name = ?, address = ?, ");
		sql.append(" telephone = ?, gender = ?, relationship = ?, ");
		sql.append(" birthday = ?, comments = ? where id = ? ");
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			
			
//			pstmt.setInt(1, vo.getId());
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getAddress());
			pstmt.setString(3, vo.getTelephone());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getRelationship());
			pstmt.setString(6, vo.getBirthday());
			pstmt.setString(7, vo.getComments());
			pstmt.setInt(8, vo.getId());
			
		
//		result = pstmt.executeUpdate();
//		
//		if(result == 1) {
//			System.out.println("데이터가 수정되었습니다.");
//			vo.setResult(result);
//		}
		if (pstmt.executeUpdate() < 1) {
			String msg = "데이터 수정에 실패했습니다.";
			System.out.println(msg);
		} else {
			System.out.println("데이터 수정에 성공했습니다.");
			vo.setResult(1);

		}
//			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionMgr.freeConnection(pstmt, con);
		}
		
		return null;
	}

}
