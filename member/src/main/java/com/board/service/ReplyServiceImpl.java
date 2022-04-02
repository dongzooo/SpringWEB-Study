package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.Criteria;
import com.board.domain.ReplyVO;
import com.board.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	public  ReplyMapper replyMapper;
	
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("register : "+vo);
		return replyMapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		log.info("get rno : "+rno);
		
		return replyMapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("modify : "+vo);
		return replyMapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		log.info("remove : "+rno);
		return replyMapper.delete(rno);
	}

	@Override
	public List<ReplyVO> getList(Long bno, Criteria cri) {
		// TODO Auto-generated method stub
		log.info("get reply list bno: "+ bno);
		log.info("get reply list cri: "+ cri);
		return replyMapper.getListWithPaging(bno, cri);
	}
}
