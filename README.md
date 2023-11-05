# MarcHighSpeedRail
EEIT172_Group3_Final_Project


4th Nov 張益齊 Log [ hibernate Many to one lazy loading]
Entity 的ManyToOne field 要加上
	@JsonIgnore  // will fix Lazy loading on many to one problem
	@JsonManagedReference  /// will avoid inifinition Loop for reference .
 之後當你在讀取該entity時才可以不會報錯
