package csen1002.tests.task5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task5.CfgLeftRecElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task5TestsBatch4 {

	@Test
	public void testCfgLeftRecursionElimination1() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;W;E;V;X;L#p;u;v#S/SVWpL,VXEXp,XLEEu,p,uWv,vS;W/WW,WWu,WvLEp,p,pLuSW;E/WV,WvL,XuE,vWuLu;V/ESEL,LvX,SEu,WV,u,vWVVv;X/WX,XSW,XV,XvV,pXEEW;L/SuEu,pLE,vLLv,vXvWS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;W;E;V;X;L;S';W';V';X';L'#p;u;v#S/VXEXpS',XLEEuS',pS',uWvS',vSS';W/pW',pLuSWW';E/pW'V,pLuSWW'V,pW'vL,pLuSWW'vL,XuE,vWuLu;V/pW'VSELV',pLuSWW'VSELV',pW'vLSELV',pLuSWW'vLSELV',XuESELV',vWuLuSELV',LvXV',XLEEuS'EuV',pS'EuV',uWvS'EuV',vSS'EuV',pW'VV',pLuSWW'VV',uV',vWVVvV';X/pW'XX',pLuSWW'XX',pXEEWX';L/pW'VSELV'XEXpS'uEuL',pLuSWW'VSELV'XEXpS'uEuL',pW'vLSELV'XEXpS'uEuL',pLuSWW'vLSELV'XEXpS'uEuL',pW'XX'uESELV'XEXpS'uEuL',pLuSWW'XX'uESELV'XEXpS'uEuL',pXEEWX'uESELV'XEXpS'uEuL',vWuLuSELV'XEXpS'uEuL',pW'XX'LEEuS'EuV'XEXpS'uEuL',pLuSWW'XX'LEEuS'EuV'XEXpS'uEuL',pXEEWX'LEEuS'EuV'XEXpS'uEuL',pS'EuV'XEXpS'uEuL',uWvS'EuV'XEXpS'uEuL',vSS'EuV'XEXpS'uEuL',pW'VV'XEXpS'uEuL',pLuSWW'VV'XEXpS'uEuL',uV'XEXpS'uEuL',vWVVvV'XEXpS'uEuL',pW'XX'LEEuS'uEuL',pLuSWW'XX'LEEuS'uEuL',pXEEWX'LEEuS'uEuL',pS'uEuL',uWvS'uEuL',vSS'uEuL',pLEL',vLLvL',vXvWSL';S'/VWpLS',e;W'/WW',WuW',vLEpW',e;V'/XEXpS'EuV',e;X'/SWX',VX',vVX',e;L'/vXV'XEXpS'uEuL',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination2() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;V;X;P;K#h;m;t;w#S/PPP,StSm,VV,XSK,tKm,tSt;V/KXmK,VKKwS,VwS,hShV,mKm,wXK;X/mKPw,tVmKw;P/PtSVX,PwX,ShXKh,XXPK,wPXh;K/KXhVh,Pw,VPVhS,mS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;V;X;P;K;S';V';P';K'#h;m;t;w#S/PPPS',VVS',XSKS',tKmS',tStS';V/KXmKV',hShVV',mKmV',wXKV';X/mKPw,tVmKw;P/KXmKV'VS'hXKhP',hShVV'VS'hXKhP',mKmV'VS'hXKhP',wXKV'VS'hXKhP',mKPwSKS'hXKhP',tVmKwSKS'hXKhP',tKmS'hXKhP',tStS'hXKhP',mKPwXPKP',tVmKwXPKP',wPXhP';K/hShVV'VS'hXKhP'wK',mKmV'VS'hXKhP'wK',wXKV'VS'hXKhP'wK',mKPwSKS'hXKhP'wK',tVmKwSKS'hXKhP'wK',tKmS'hXKhP'wK',tStS'hXKhP'wK',mKPwXPKP'wK',tVmKwXPKP'wK',wPXhP'wK',hShVV'PVhSK',mKmV'PVhSK',wXKV'PVhSK',mSK';S'/tSmS',e;V'/KKwSV',wSV',e;P'/tSVXP',wXP',PPS'hXKhP',e;K'/XhVhK',XmKV'VS'hXKhP'wK',XmKV'PVhSK',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination3() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Q;W;C;N;L#k;r;u;v;x#S/Sx,WCxS,WQWQ,uNx,xQNCS;Q/Cr,LSL,k;W/QQQ,WWW,WrWuC,rNC,uNk;C/LQNxQ,Nr,rNQ,vQCN,xQLWC,xW;N/CC,LLkL,NN,Nv,QvWr,uCk;L/LSC,QN,QNrCx,rWLC");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Q;W;C;N;L;S';W';N';L'#k;r;u;v;x#S/WCxSS',WQWQS',uNxS',xQNCSS';Q/Cr,LSL,k;W/CrQQW',LSLQQW',kQQW',rNCW',uNkW';C/LQNxQ,Nr,rNQ,vQCN,xQLWC,xW;N/LQNxQCN',rNQCN',vQCNCN',xQLWCCN',xWCN',LLkLN',LQNxQrvWrN',rNQrvWrN',vQCNrvWrN',xQLWCrvWrN',xWrvWrN',LSLvWrN',kvWrN',uCkN';L/rNQCN'rrNL',vQCNCN'rrNL',xQLWCCN'rrNL',xWCN'rrNL',rNQrvWrN'rrNL',vQCNrvWrN'rrNL',xQLWCrvWrN'rrNL',xWrvWrN'rrNL',kvWrN'rrNL',uCkN'rrNL',rNQrNL',vQCNrNL',xQLWCrNL',xWrNL',kNL',rNQCN'rrNrCxL',vQCNCN'rrNrCxL',xQLWCCN'rrNrCxL',xWCN'rrNrCxL',rNQrvWrN'rrNrCxL',vQCNrvWrN'rrNrCxL',xQLWCrvWrN'rrNrCxL',xWrvWrN'rrNrCxL',kvWrN'rrNrCxL',uCkN'rrNrCxL',rNQrNrCxL',vQCNrNrCxL',xQLWCrNrCxL',xWrNrCxL',kNrCxL',rWLCL';S'/xS',e;W'/WWW',rWuCW',e;N'/rCN',NN',vN',rrvWrN',e;L'/SCL',QNxQrNL',QNxQCN'rrNL',LkLN'rrNL',QNxQrvWrN'rrNL',SLvWrN'rrNL',SLNL',QNxQrNrCxL',QNxQCN'rrNrCxL',LkLN'rrNrCxL',QNxQrvWrN'rrNrCxL',SLvWrN'rrNrCxL',SLNrCxL',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination4() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Z;O;N;J;K#j;n;r;u;x#S/SS,SZxOJ,ZSxOj,nO;Z/NNnZ,NxO,ZNuO,Zu,uNJOK;O/NrO,SNnZ,ZK,ZOJ,nOOS;N/JJ,NKnOr,nNKZJ,x;J/NSSO,nJZuZ;K/JZuZN,NjJn,jNjNn");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Z;O;N;J;K;S';Z';N';J'#j;n;r;u;x#S/ZSxOjS',nOS';Z/NNnZZ',NxOZ',uNJOKZ';O/NrO,NNnZZ'SxOjS'NnZ,NxOZ'SxOjS'NnZ,uNJOKZ'SxOjS'NnZ,nOS'NnZ,NNnZZ'K,NxOZ'K,uNJOKZ'K,NNnZZ'OJ,NxOZ'OJ,uNJOKZ'OJ,nOOS;N/JJN',nNKZJN',xN';J/nNKZJN'SSOJ',xN'SSOJ',nJZuZJ';K/nNKZJN'SSOJ'ZuZN,xN'SSOJ'ZuZN,nJZuZJ'ZuZN,nNKZJN'SSOJ'JN'jJn,xN'SSOJ'JN'jJn,nJZuZJ'JN'jJn,nNKZJN'jJn,xN'jJn,jNjNn;S'/SS',ZxOJS',e;Z'/NuOZ',uZ',e;N'/KnOrN',e;J'/JN'SSOJ',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination5() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;A;M;R;H;Z;N#l;t;w#S/RRM,SHAtR,StAR;A/AMtAS,AS,lSlM,tAA,tMZA;M/MH,RZZ,wS;R/AS,tHMH,wNlN;H/NlAR,SM,tNSH;Z/HMS,HSNZ,NNlR,t;N/HwHMS,ZA,lMwZ,tAZZZ,w,wA");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;A;M;R;H;Z;N;S';A';M';N'#l;t;w#S/RRMS';A/lSlMA',tAAA',tMZAA';M/RZZM',wSM';R/lSlMA'S,tAAA'S,tMZAA'S,tHMH,wNlN;H/NlAR,lSlMA'SRMS'M,tAAA'SRMS'M,tMZAA'SRMS'M,tHMHRMS'M,wNlNRMS'M,tNSH;Z/NlARMS,lSlMA'SRMS'MMS,tAAA'SRMS'MMS,tMZAA'SRMS'MMS,tHMHRMS'MMS,wNlNRMS'MMS,tNSHMS,NlARSNZ,lSlMA'SRMS'MSNZ,tAAA'SRMS'MSNZ,tMZAA'SRMS'MSNZ,tHMHRMS'MSNZ,wNlNRMS'MSNZ,tNSHSNZ,NNlR,t;N/lSlMA'SRMS'MwHMSN',tAAA'SRMS'MwHMSN',tMZAA'SRMS'MwHMSN',tHMHRMS'MwHMSN',wNlNRMS'MwHMSN',tNSHwHMSN',lSlMA'SRMS'MMSAN',tAAA'SRMS'MMSAN',tMZAA'SRMS'MMSAN',tHMHRMS'MMSAN',wNlNRMS'MMSAN',tNSHMSAN',lSlMA'SRMS'MSNZAN',tAAA'SRMS'MSNZAN',tMZAA'SRMS'MSNZAN',tHMHRMS'MSNZAN',wNlNRMS'MSNZAN',tNSHSNZAN',tAN',lMwZN',tAZZZN',wN',wAN';S'/HAtRS',tARS',e;A'/MtASA',SA',e;M'/HM',e;N'/lARwHMSN',lARMSAN',lARSNZAN',NlRAN',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination6() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;A;E;N;U;L#g;h;s#S/NgLUS,SUNLN,SsEgE,sEgNs;A/h,hLENE;E/LN,g,gUhNN,hULgS,sNsL;N/NSNN,NUS,sUsAA;U/NEgE,ShENU;L/ASANL,EgN,NsEsN,gNh,h,sEsL");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;A;E;N;U;L;S';N';L'#g;h;s#S/NgLUSS',sEgNsS';A/h,hLENE;E/LN,g,gUhNN,hULgS,sNsL;N/sUsAAN';U/sUsAAN'EgE,sUsAAN'gLUSS'hENU,sEgNsS'hENU;L/hSANLL',hLENESANLL',ggNL',gUhNNgNL',hULgSgNL',sNsLgNL',sUsAAN'sEsNL',gNhL',hL',sEsLL';S'/UNLNS',sEgES',e;N'/SNNN',USN',e;L'/NgNL',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination7() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;N;Q;K;L;H#m;n;q;x;y#S/Qy,SNnNH,SmKKy,nQ;N/qN,xLSS;Q/q,yNqKL;K/LmQ,QHN,SHHN,mKQK,qQ;L/LQ,Ly,QK,QLxQx,SQx,SmLQ;H/HNKxK,NmSmL,Nq,NyKL,qQyS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;N;Q;K;L;H;S';L';H'#m;n;q;x;y#S/QyS',nQS';N/qN,xLSS;Q/q,yNqKL;K/LmQ,qHN,yNqKLHN,qyS'HHN,yNqKLyS'HHN,nQS'HHN,mKQK,qQ;L/qKL',yNqKLKL',qLxQxL',yNqKLLxQxL',qyS'QxL',yNqKLyS'QxL',nQS'QxL',qyS'mLQL',yNqKLyS'mLQL',nQS'mLQL';H/qNmSmLH',xLSSmSmLH',qNqH',xLSSqH',qNyKLH',xLSSyKLH',qQySH';S'/NnNHS',mKKyS',e;L'/QL',yL',e;H'/NKxKH',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination8() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;H;M;C;X;Q#b;c;n#S/MMSH,bHnH,nHQ;H/Sn,XCcXM,Xc;M/CC,McXX,c,cCS,nX;C/Cc,CcCn,QbXSb,Qc;X/CbXn,Mn,cC,nCQ,nH;Q/CcSXX,HbQQQ,QHXMS,QcH,SS");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;H;M;C;X;Q;M';C';Q'#b;c;n#S/MMSH,bHnH,nHQ;H/MMSHn,bHnHn,nHQn,XCcXM,Xc;M/CCM',cM',cCSM',nXM';C/QbXSbC',QcC';X/QbXSbC'bXn,QcC'bXn,QbXSbC'CM'n,QcC'CM'n,cM'n,cCSM'n,nXM'n,cC,nCQ,nH;Q/cM'MSHnbQQQQ',cCSM'MSHnbQQQQ',nXM'MSHnbQQQQ',bHnHnbQQQQ',nHQnbQQQQ',cM'nCcXMbQQQQ',cCSM'nCcXMbQQQQ',nXM'nCcXMbQQQQ',cCCcXMbQQQQ',nCQCcXMbQQQQ',nHCcXMbQQQQ',cM'ncbQQQQ',cCSM'ncbQQQQ',nXM'ncbQQQQ',cCcbQQQQ',nCQcbQQQQ',nHcbQQQQ',cM'MSHSQ',cCSM'MSHSQ',nXM'MSHSQ',bHnHSQ',nHQSQ';M'/cXXM',e;C'/cC',cCnC',e;Q'/bXSbC'cSXXQ',cC'cSXXQ',bXSbC'CM'MSHnbQQQQ',cC'CM'MSHnbQQQQ',bXSbC'bXnCcXMbQQQQ',cC'bXnCcXMbQQQQ',bXSbC'CM'nCcXMbQQQQ',cC'CM'nCcXMbQQQQ',bXSbC'bXncbQQQQ',cC'bXncbQQQQ',bXSbC'CM'ncbQQQQ',cC'CM'ncbQQQQ',HXMSQ',cHQ',bXSbC'CM'MSHSQ',cC'CM'MSHSQ',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination9() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;Z;C;U;Q#b;k;s#S/UsZ,sS;Z/CkUs,bUkSS,kCZUC;C/CbSb,CbUU,bUC,bZ,sUQUU;U/QS,SbCQC,SkUU,UC;Q/QCUC,QZbSb,Sk,ZUQCC,Zb,b");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;Z;C;U;Q;C';U';Q'#b;k;s#S/UsZ,sS;Z/CkUs,bUkSS,kCZUC;C/bUCC',bZC',sUQUUC';U/QSU',sSbCQCU',sSkUUU';Q/sSbCQCU'sZkQ',sSkUUU'sZkQ',sSkQ',bUCC'kUsUQCCQ',bZC'kUsUQCCQ',sUQUUC'kUsUQCCQ',bUkSSUQCCQ',kCZUCUQCCQ',bUCC'kUsbQ',bZC'kUsbQ',sUQUUC'kUsbQ',bUkSSbQ',kCZUCbQ',bQ';C'/bSbC',bUUC',e;U'/sZbCQCU',sZkUUU',CU',e;Q'/CUCQ',ZbSbQ',SU'sZkQ',e", cfgLeftRecElim.toString());
	}

	@Test
	public void testCfgLeftRecursionElimination10() {
		CfgLeftRecElim cfgLeftRecElim= new CfgLeftRecElim("S;F;J;C;B;G;H#a;b;r;t;v#S/BbCGH,HaS,SG,bCb,r,vG;F/FJ,FSFF,vSaF;J/bJ,rBH,t,tJBF,vGaC;C/aBH,aJr;B/BJrG,BaSrB,GFJ,GvF,a;G/FJ,Sb;H/FC,Fa,GbC,SJ,rHb,tB");
		cfgLeftRecElim.eliminateLeftRecursion();
		assertEquals("S;F;J;C;B;G;H;S';F';B';G';H'#a;b;r;t;v#S/BbCGHS',HaSS',bCbS',rS',vGS';F/vSaFF';J/bJ,rBH,t,tJBF,vGaC;C/aBH,aJr;B/GFJB',GvFB',aB';G/vSaFF'JG',aB'bCGHS'bG',HaSS'bG',bCbS'bG',rS'bG',vGS'bG';H/vSaFF'CH',vSaFF'aH',vSaFF'JG'bCH',aB'bCGHS'bG'bCH',bCbS'bG'bCH',rS'bG'bCH',vGS'bG'bCH',vSaFF'JG'FJB'bCGHS'JH',aB'bCGHS'bG'FJB'bCGHS'JH',bCbS'bG'FJB'bCGHS'JH',rS'bG'FJB'bCGHS'JH',vGS'bG'FJB'bCGHS'JH',vSaFF'JG'vFB'bCGHS'JH',aB'bCGHS'bG'vFB'bCGHS'JH',bCbS'bG'vFB'bCGHS'JH',rS'bG'vFB'bCGHS'JH',vGS'bG'vFB'bCGHS'JH',aB'bCGHS'JH',bCbS'JH',rS'JH',vGS'JH',rHbH',tBH';S'/GS',e;F'/JF',SFFF',e;B'/JrGB',aSrBB',e;G'/FJB'bCGHS'bG',vFB'bCGHS'bG',e;H'/aSS'bG'bCH',aSS'bG'FJB'bCGHS'JH',aSS'bG'vFB'bCGHS'JH',aSS'JH',e", cfgLeftRecElim.toString());
	}

}