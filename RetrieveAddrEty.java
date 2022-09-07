package address.view3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import address.view3.DBConnectionMgr;
import address.AddressBook;
import address.view3.AddressVO;


public class RetrieveAddrEty {
	DBConnectionMgr dbMgr = new DBConnectionMgr();
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
   /***************************************************************************
    * 회원정보 중 상세보기 구현 - 1건 조회
    * SELECT id, name, address, DECODE(gender,'1','남','여') as "성별"
            , relationship, comments, registedate, birthday
         FROM mkaddrtb
        WHERE id = 5
    * @param vo : 사용자가 선택한 값
    * @return AddressVO : 조회 결과 1건을 담음
    **************************************************************************/
   public AddressVO retrieve(AddressVO vo) {
      System.out.println("RetrieveAddrEty retrieve(AddressVO vo) 호출 성공");
      
  	AddressVO rVO = null;
		StringBuilder sql = new StringBuilder();

		sql.append("   SELECT id, name, address, telephone       						");
      sql.append("   , gender, relationship, comments   ");
      sql.append("   , registedate, birthday											");
      sql.append("   FROM mkaddrtb                                                    ");
      sql.append("   WHERE id = ?                                                 	");
		
		try {
      	con = dbMgr.getConnection();
      	pstmt = con.prepareStatement(sql.toString());
      	pstmt.setInt(1, vo.getId());
      	
      	rs = pstmt.executeQuery();
      	
      	if(rs.next()) {
      		rVO = new AddressVO();
      		rVO.setId(rs.getInt("id"));
      		rVO.setName(rs.getString("name"));
      		rVO.setAddress(rs.getString("address"));
      		rVO.setTelephone(rs.getString("telephone"));
      		rVO.setGender(rs.getString("gender"));
      		rVO.setRelationship(rs.getString("relationship"));
      		rVO.setComments(rs.getString("comments"));
      		rVO.setRegistedate(rs.getString("registedate"));
      		rVO.setBirthday(rs.getString("birthday"));
      		
      		
      	}
      	
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			DBConnectionMgr.freeConnection(rs, pstmt, con);
		}
		
		return rVO;
   
      
   }
   /***************************************************************************
    * 회원 목록 보기 구현 - n건 조회
    * SELECT id, name, address, DECODE(gender,'1','남','여') as "성별"
            , relationship, comments, registedate, birthday
         FROM mkaddrtb
    * @param vo : 사용자가 선택한 값
    * @return AddressVO : 조회 결과 1건을 담음
    **************************************************************************/
   public AddressVO[] retrieve() {
      
      DBConnectionMgr dbMgr = new DBConnectionMgr();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
   
      System.out.println("RetrieveAddrEty retrieve() 호출 성공");
      
      AddressVO[] vos = null;
      StringBuilder sql = new StringBuilder();

      sql.append("   SELECT id, name, address, telephone                         ");
        sql.append("   , DECODE(gender,'1','m','w') as gender, relationship, comments   ");
        sql.append("   , registedate, birthday                                 ");
        sql.append("   FROM mkaddrtb                                                    ");

 try {
           con = dbMgr.getConnection();
           pstmt = con.prepareStatement(sql.toString());
           rs = pstmt.executeQuery();
       
       ////vos = new Object[v.size()];
       ////v.copyInto(vos);
       //////vos = (AddressVO[]) v.toArray(new AddressVO[v.size()]);
       //
       ////vos = new AddressVO[v.size()];
       ////vos = (AddressVO[]) v.toArray(new AddressVO[v.size()]); //타입다름 java.lang.ArrayStoreException
       ////  vos = (AddressVO[]) v.toArray(new Object[v.size()]); //타입적절치않음 java.lang.ClassCastException
       //  
       //////v.copyInto(vos);//단독으로 쓰면 null / vos = new AddressVO[v.size()]; 얘랑 같이 쓰면  java.lang.ArrayStoreException
       //
       ////System.arraycopy(v, 0, vos, 0, v.size()); // 벡터 = 벡터가 어레이가 아니라 뜸, 어레이리스트 = 눌포인트
       //Object[] k = null;
       //k = new Object[v.size()];
       ////Object[] k = new AddressVO[v.size()];
       ////v.copyInto(k);
       //// AddressVO[] k1 = null;
       //// AddressVO[] k1 = (AddressVO[]) v.toArray(new AddressVO[v.size()]); //java.lang.ArrayStoreException
       //// Object[] k = v.toArray(new Object[v.size()]); //java.lang.ArrayStoreException
       //System.arraycopy(v, 0, k, 0, v.size()); 
////             System.arraycopy(v, 0, k1, 0, v.size()); 
       //System.out.println(Arrays.toString(k));
       ////System.out.println(Arrays.toString(k1));
       ////vos = new AddressVO[k.length];
       ////System.arraycopy(k, 0, vos, 0, k.length); 
       ////vos = new AddressVO[k1.length];
       ////System.arraycopy(k1, 0, vos, 0, k1.length); 
           
          AddressVO im = null;
          Vector<AddressVO> si = new Vector<>();
         while (rs.next()) {
        	 int id = rs.getInt("id");
             String name = rs.getString("name");
             String address = rs.getString("address");
             String telephone = rs.getString("telephone");
             String gender = rs.getString("gender");
             String relationship = rs.getString("relationship");
             String comments = rs.getString("comments");
             String registedate = rs.getString("registedate");
             String birthday = rs.getString("birthday");
                 
                 im = new AddressVO(name, address,telephone, gender
                                        , relationship, comments, registedate, birthday, id);
                         si.add(im);
                      }
         

         
         vos = new AddressVO[si.size()];
         si.copyInto(vos);
        
      } catch (Exception e) {
         System.out.println(e.toString());
      } finally {
    	  
         DBConnectionMgr.freeConnection(rs, pstmt, con);
    	  try {				
    		  if(rs != null) rs.close();				
    		  if(pstmt != null) pstmt.close();				
    		  if(con != null) con.close();			
    		  } catch (SQLException e2) 
    	  {				// TODO Auto-generated catch block				
    			  e2.printStackTrace();		
    			  }
      }

      return vos;
   }
   
}

/*
ID,NAME,ADDRESS,TELEPHONE,GENDER,RELATIONSHIP,BIRTHDAY,COMMENTS,REGISTEDATE
3,나초보,서울시 마포구 공덕동,025556968,2,2,19801215,주연테크,REGISTEDATE
1,홍길동,서울시 영등포구 당산동,111,1,동창,19901010,222,20081117
2,이순신,서울시 서초구 양재동,222,2,회사동료,19901110,333,20100113
4,강감찬,경기도 광명시,11,1,동창,19801120,테스트합니다.,2011-03-20
9,나초조,4455,44455,1,44555,19800702,자영업,20110320
7,223,223,223,2,223,223,223,20190609
10,조조,경기도 화성시,44455,1,44555,19800702,자영업,20110320
*/