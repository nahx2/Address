package address.view3;

import java.sql.Connection;
import java.sql.PreparedStatement;

import address.DBConnectionMgr;

public class DeleteAddrEty {

	public AddressVO delete(AddressVO vo) {
		
		System.out.println("DeleteAddrEty delete 호출 성공");
		
		DBConnectionMgr dbMgr = new DBConnectionMgr();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM MKADDRTB ");
		sql.append("		WHERE id = ?  ");
		
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getId());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("데이터가 삭제되었습니다.");
				vo.setResult(pstmt.executeUpdate());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnectionMgr.freeConnection(pstmt, con);
		}
		
		return null;
	}

}
