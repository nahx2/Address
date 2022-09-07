package address.view3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterAddrEty {
   /****************************************************************************
    * 회원등록 구현 INSERT INTO mkaddrtb(id, name, address, telephone , gender,
    * relationship, birthday , comments, registedate) values(11,'나신입','서울시 강남구
    * 대치동','0105557777' ,1, '회사동료', '1999-10-27' ,'Front-End개발자', '2022-04-16')
    * 
    * @param vo - 사용자가 입력한 값 담기
    * @return - 등록 성공 여부 담기
    ***************************************************************************/

	Connection con = null;
	PreparedStatement pstmt = null;
	// ResultSet rs = null;
	// int result = vo.getResult();
	StringBuilder sql = new StringBuilder();
	
   public AddressVO register(AddressVO vo) {

	   System.out.println("RegisterAddrEty register 호출 성공");

      sql.append("  INSERT INTO mkaddrtb(name, address, telephone         ");
      sql.append("          , gender, relationship, birthday             ");
      sql.append("          , comments, registedate, id)                  ");
      sql.append("     values(?, ?, ?                              ");
      sql.append("           ,?, ?, ?                                  ");
      sql.append("           ,?, to_char(sysdate,'YYYY,MM,DD'), seq_mkaddrtb_id.nextval)   ");
      DBConnectionMgr dbMgr = new DBConnectionMgr();

      try {

         con = dbMgr.getConnection();
         pstmt = con.prepareStatement(sql.toString());
         
         
         pstmt.setString(1, vo.getName());
         pstmt.setString(2, vo.getAddress());
         pstmt.setString(3, vo.getTelephone());
         pstmt.setString(4, vo.getGender());
         pstmt.setString(5, vo.getRelationship());
         pstmt.setString(6, vo.getBirthday());
         pstmt.setString(7, vo.getComments());
         // pstmt.setString(8, vo.getRegistedate());
         // pstmt.setInt(1, vo.getId());

         if (pstmt.executeUpdate() < 1) {
            String msg = "데이터 입력에 실패했습니다.";
            System.out.println(msg);
         } else {
            System.out.println("데이터 입력에 성공했습니다.");
            vo.setResult(1);
         }

      } catch (Exception e) {
         System.out.println(e.toString());
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
         }
      }
    
      return vo;
   }
}