# MarcHighSpeedRail
EEIT172_Group3_Final_Project
[團隊開發規範] ！！！請各位組員先看完這篇內容再開始開發！！！

一、專案package結構  [ 每人一個package群集，該群集下面有controller, model, service, repository] !!

com.myHighSpeedRail

	- Marc
 	----controller
  	----model
   	----repository
    	----service
     
     	- peter
      	----controller
       	----model
	----service
 	----repository
  	
  	- yuhsin
   	----controller
  	----model
   	----repository
    	----service
     
     	- johnny
      	----controller
  	----model
   	----repository
    	----service
     
     	- derekwu
      	----controller
  	----model
   	----repository
    	----service
二、如果你要使用到別人系統下的Dao請在你的serivce下用@Autowired獲取他人的service進行操作，！！不要直接使用他人的Dao！！
![image](https://github.com/YIIIIIACH/MarcHighSpeedRail/assets/42449396/fee8af52-45b5-40a7-877c-8821ca936e04)
這樣後面程式會比較好維護

example:我要使用他人的service
public class myService{
	@Autowired
 	private OtherPeopleService ops;
  ........
  }

三、命名規範 存在資料庫了都用蛇行命名 ex:xxx_ooo_aaa  以java程式存在的都以駱駝式命名 ex aaAaaAaaAaa.java
資料表欄位如果是foreign key 一律後面結尾加上 "_fk" 
example
xxxxModel.java
@Entity
@Table(name="xxx_xxxx_xxx")
public class xxxXxxxXxx{
	@Id
 	@Column(name="oo_oo_oo")
  	private Integer ooOoOo;

    	@Column(name="tt_tt_tt_fk")// 資料表欄位是參照鍵一律加底線fk
     	private String ttTtTt; // model的field名稱不用因為是參照鍵就加上fk [[不用加fk]]
}


4th Nov 張益齊 Log [ hibernate Many to one lazy loading]
Entity 的ManyToOne field 要加上
	@JsonIgnore  // will fix Lazy loading on many to one problem
	@JsonManagedReference  /// will avoid inifinition Loop for reference .
 之後當你在讀取該entity時才可以不會報錯
